package com.unchk.formulaire

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.unchk.formulaire.basededon.unchkbd

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflateId")
    lateinit var bd:unchkbd
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initialisation
        bd = unchkbd(this)
//findViewById permet de recuperer les identifiants des champs
        val register = findViewById<Button>(R.id.ins)
        val nom = findViewById<EditText>(R.id.text)
        val prenom = findViewById<EditText>(R.id.editTextText2)
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val pass = findViewById<EditText>(R.id.editTextTextPassword)
        val btn = findViewById<TextView>(R.id.textView3)

        register.setOnClickListener{
    //            permet d obliger d entrer quelque chose dans le champs
            val txtnom = nom.text.toString()
            val txtprenom = prenom.text.toString()
            val txtemail = email.text.toString()
            val txtpass = pass.text.toString()

    //            condition pour voir si le champ son vide
    //            isEmpty(remplir tout les champs)
//            if(txtnom.isEmpty() || txtprenom.isEmpty() || txtemail.isEmpty() || txtpass.isEmpty()){
//                //            affiche le message veuiller remplir... si on clik sur inscrire
//                Toast.makeText( this,"  Veuillez remplir tout les champs!", Toast.LENGTH_LONG).show()
//            }
            if (txtnom.isEmpty() || txtprenom.isEmpty() || txtemail.isEmpty() || txtpass.isEmpty()){
                val user = User(0, txtnom,txtprenom,txtemail,txtpass)
                val insert= bd.ajout(user)
                if(insert){
                    nom.setText("")
                    prenom.setText("")
                    email.setText("")
                    pass.setText("")
                    Toast.makeText( this," votre compte a ete creer avec succé", Toast.LENGTH_LONG).show()
                    val intentLogin = Intent( this, LoginActivity::class.java)
                    startActivity(intentLogin)
                } else{
                    Toast.makeText( this,"Erreur de création de compte", Toast.LENGTH_LONG).show()
                }

            }


        }
//        pour aller dans la page loginactivity si on  clik sur se connecter
btn.setOnClickListener{
    val intentLogin = Intent( this, LoginActivity::class.java)
    startActivity(intentLogin)
}
    }
}