package com.primera.donaya;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.primera.donaya.databinding.ActivityInicioBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class InicioActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInicioBinding binding;


    private TextView userNombre, userEmail, userID, MenuuserNombre,MenuuserEmail;
    private CircleImageView userImg, Menuuserimg;
    private FirebaseAuth mAuth;
    Button btnCerrarSesion, btnEliminarCta;
    String MiWhatsapp = "https://wa.me/75035260";

    FloatingActionButton btnwhat;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    Limpio limpio = new Limpio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarInicio.toolbar);




        /*MenuuserNombre = findViewById(R.id.MenuuserNombre);
        MenuuserEmail = findViewById(R.id.MenuuserCorreo);
        Menuuserimg = findViewById(R.id.MenuuserImagen);
        userNombre = findViewById(R.id.userNombre);
        userEmail = findViewById(R.id.userEmail);
        userID = findViewById(R.id.userId);
        userImg = findViewById(R.id.userImagen);
        btnCerrarSesion = findViewById(R.id.btnLogout);
        btnEliminarCta = findViewById(R.id.btnEliminarCta);

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
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


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
                            Intent mainActivity = new Intent(getApplicationContext(), Login.class);
                            Toast.makeText(InicioActivity.this, "Ha cerrado sesión", Toast.LENGTH_SHORT).show();
                            startActivity(mainActivity);
                            InicioActivity.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google",
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
                GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
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
                                        Log.d("InicioActivity", "onSuccess:Usuario Eliminado");
                                        //llamar al metodo signOut para salir de aqui

                                        Toast.makeText(InicioActivity.this, "Usuario Eliminado Correctamente", Toast.LENGTH_SHORT).show();
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
        });//fin onClick*/




        binding.appBarInicio.fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Uri _link = Uri.parse(MiWhatsapp);
                Intent i = new Intent(Intent.ACTION_VIEW, _link);
                startActivity(i);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_organizaciones)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
                Intent IntentMainActivity = new Intent(getApplicationContext(), Login.class);
                IntentMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(IntentMainActivity);
                InicioActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_logout:

                this.navlogout();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private void navlogout() {


        //Cerrar sesion con firebase
        mAuth.signOut();

        //Cerrar sesión con google tambien: Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //Abrir MainActivity con SigIn button
                if(task.isSuccessful()){
                    Intent mainActivity = new Intent(getApplicationContext(), Login.class);
                    Toast.makeText(InicioActivity.this, "Ha cerrado sesión", Toast.LENGTH_SHORT).show();
                    startActivity(mainActivity);
                    InicioActivity.this.finish();
                }else{
                    Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}