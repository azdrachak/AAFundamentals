package com.github.azdrachak.aafundamentals

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.azdrachak.aafundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.helloWorld.setOnClickListener {
            val intent = Intent(this, MovieDetail::class.java)
            startActivity(intent)
        }

    }
}