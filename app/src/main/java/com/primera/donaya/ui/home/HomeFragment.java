package com.primera.donaya.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.primera.donaya.InicioActivity;
import com.primera.donaya.Limpio;
import com.primera.donaya.Login;
import com.primera.donaya.R;
import com.primera.donaya.databinding.FragmentHomeBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private TextView userNombre, userEmail, userID, MenuuserNombre,MenuuserEmail;
    private CircleImageView userImg, Menuuserimg;
    private FirebaseAuth mAuth;
    Button btnCerrarSesion, btnEliminarCta;
    Limpio limpio = new Limpio();

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MenuuserNombre = root.findViewById(R.id.MenuuserNombre);
        MenuuserEmail = root.findViewById(R.id.MenuuserCorreo);
        Menuuserimg = root.findViewById(R.id.MenuuserImagen);
        userNombre = root.findViewById(R.id.userNombre);
        userEmail = root.findViewById(R.id.userEmail);
        userID = root.findViewById(R.id.userId);
        userImg = root.findViewById(R.id.userImagen);
        btnCerrarSesion = root.findViewById(R.id.btnLogout);
        btnEliminarCta = root.findViewById(R.id.btnEliminarCta);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        userNombre.setText(currentUser.getDisplayName());
        userEmail.setText(currentUser.getEmail());
        userID.setText(currentUser.getUid());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(userImg);




        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cerrar sesion con firebase
                mAuth.signOut();

                //Cerrar sesión con google tambien: Google sign out
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Abrir MainActivity con SigIn button
                        if(task.isSuccessful()){
                            Intent mainActivity = new Intent(getContext(), Login.class);
                            Toast.makeText(getContext(), "Ha cerrado sesión", Toast.LENGTH_SHORT).show();
                            startActivity(mainActivity);
                            getActivity().onBackPressed();
                        }else{
                            Toast.makeText(getContext(), "No se pudo cerrar sesión con google",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });


        btnEliminarCta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener el usuario actual
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Get the account
                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
                if (signInAccount != null) {
                    AuthCredential credential =
                            GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
                    //Re-autenticar el usuario para eliminarlo
                    user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Eliminar el usuario
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("HomeFragment", "onSuccess:Usuario Eliminado");
                                        //llamar al metodo signOut para salir de aqui

                                        Toast.makeText(getContext(), "Usuario Eliminado Correctamente", Toast.LENGTH_SHORT).show();
                                        signOut();
                                    }
                                });
                            } else {
                                Log.e("InicioActivity", "onComplete: Error al eliminar el usuario",
                                        task.getException());
                            }
                        }
                    });
                } else {
                    Log.d("InicioActivity", "Error: reAuthenticateUser: user account is null");
                }
            }
        });







        return root;
    }




    private void signOut() {
        //sign out de firebase
        FirebaseAuth.getInstance().signOut();
        //sign out de "google sign in"
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //regresar al login screen o MainActivity
                //Abrir mainActivity para que inicie sesión o sign in otra vez.
                Intent IntentMainActivity = new Intent(getContext(), Login.class);
                IntentMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(IntentMainActivity);
                getActivity().onBackPressed();
            }
        });
    }
}