package com.unchk.formulaire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.unchk.formulaire.basededon.unchkbd

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         val bd= unchkbd(this)

        val mail= findViewById<EditText>(R.id.emailadd)
        val mps = findViewById<EditText>(R.id.mdp)
        val login = findViewById<Button>(R.id.connect)
        val createCompte =findViewById<TextView>(R.id.textView4)


        login.setOnClickListener{
            val txtmail= mail.text.toString()
            val txtmps= mps.text.toString()


            if(txtmail.isEmpty() || txtmps.isEmpty()){
                //            affiche le message bonjour t le monde si on clik sur inscrire
                Toast.makeText( this," Veuillez remplir tout les champs !", Toast.LENGTH_LONG).show()
            }else{

                val userExist = bd.conex(txtmail,txtmps)
                if(userExist != null) {
                    mail.setText("")
                    mps.setText("")

                    val intentAcceuil = Intent( this, acceuilActivity::class.java)
                    startActivity(intentAcceuil)
                }else{
                    Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_LONG).show()
                }

            }

        }

    }
}