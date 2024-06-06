package com.unchk.formulaire.basededon

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.unchk.formulaire.User

class unchkbd(
    monContext: Context,
    monBD:String = NOMBDD,
    version : Int= obversion
):SQLiteOpenHelper (
    monContext,
    monBD,
    null,
    version
){
    override fun onCreate(bd: SQLiteDatabase?) {
//        creation de table
        val tableUser = """
            create table users(
            id integer primary key , nom varchar(20) , prenom varchar (50),
            email varchar(50), pass varchar (15)
            )
        """.trimIndent()
        bd?.execSQL(tableUser)
    }

    override fun onUpgrade(bd: SQLiteDatabase?, ancVersion: Int, nVersion: Int) {
        bd?.execSQL("drop table if exists users")
    onCreate(bd)}

    fun ajout(user: User):Boolean{
    val bd= this.writableDatabase
        val values = ContentValues()
        values.put("nom",user.nom)
        values.put("prenom",user.prenom)
        values.put("email",user.email)
        values.put("pass",user.pass)
        bd.insert(NOMTABLE,null,values)
        bd.close()
        return true
    }
    @SuppressLint("Recycle")
    fun conex(email:String, pass:String):Boolean{
        val bd = this.readableDatabase
        val selection ="$EMAIL=? and $MOTPASSE=?"
        val selectionArgs = arrayOf(email , pass)
        val cursor= bd.query(NOMTABLE,null , selection , selectionArgs , null,null,null)
        val userExist = cursor.count >0
        bd.close()
        return userExist
    }


    companion object{
        private const val NOMBDD ="unchkbd"
        private const val obversion = 1
        private const val NOMTABLE ="users"
        private const val NOM ="nom"
        private const val PRENOM ="prenom"
        private const val EMAIL ="email"
        private const val MOTPASSE ="pass"
    }
}