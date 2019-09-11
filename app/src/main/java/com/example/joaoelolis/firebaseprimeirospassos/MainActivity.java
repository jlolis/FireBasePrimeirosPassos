package com.example.joaoelolis.firebaseprimeirospassos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    //objetos para manipulação dop BD
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private EditText editTextNome;
    private EditText editTextIdade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }

    public void salvarDado(View view){

        String uuid = UUID.randomUUID().toString();//Gerando um numero aleatorio.

        //inserindo Dado no FireBase dicionario(conjunto) de chaves e valores .
        databaseReference
                .child("dicionario")
                .child("356")
                .child("nome")
                .setValue(editTextNome.getText().toString());

        /*databaseReference
                .child("dicionario")
                .child(uuid) //Gerando um numero aleatorio.
                .child("idade")
                .setValue(editTextIdade.getText().toString());*/

        editTextNome.setText("");

        //editTextIdade.setText("");

    }

    //Apagar registro do banco.
    public void apagarDado(View view){

        databaseReference.child("dicionario").child("123").removeValue();
    }


    //criar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //selecionar ações para o menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Mudar para nova activity
        if(id == R.id.itemLeitura){
            Intent intent = new Intent(MainActivity.this, LeituraActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
