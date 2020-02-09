package com.example.mvvm.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.R
import com.example.mvvm.database.AppDatabase

class MainActivity : AppCompatActivity() {

    private var appDbInstance: AppDatabase = AppDatabase.getDatabaseInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
