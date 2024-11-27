package com.codegalaxy.practicewithbhanu.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codegalaxy.practicewithbhanu.UIState
import com.codegalaxy.practicewithbhanu.databinding.ActivityMainBinding
import com.codegalaxy.practicewithbhanu.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        observeUIState()

        binding.btnFetch.setOnClickListener {
            val bookUri = Uri.parse("content://com.codegalaxy.content_provider")

            val cursor = contentResolver.query(
                bookUri,
                null,
                null,
                null,
                null
            )
            cursor?.use {
                val booksInfo = StringBuilder()
                while (it.moveToNext()) {
                    val bookName = it.getString(it.getColumnIndexOrThrow("book_name"))
                    val yearPublished = it.getString(it.getColumnIndexOrThrow("year_publish"))
                    booksInfo.append("Book: $bookName, Year: $yearPublished\n")
                }
                Toast.makeText(this, booksInfo.toString(), Toast.LENGTH_LONG).show()
            }
        }


        binding.btnFragment.setOnClickListener {
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)

        }
    }

    private fun observeUIState() {
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {
                    // Show a loading indicator, for example:
                    // binding.progressBar.visibility = View.VISIBLE // Ensure you have a progressBar in your layout
                }

                is UIState.Success -> {
                    val dataResponse = state.data

                    val dataString = """
                        Name: ${dataResponse.name}
                        Year: ${dataResponse.data.year}
                        Price: ${dataResponse.data.price}
                        CPU: ${dataResponse.data.cpu}
                        Hard Disk: ${dataResponse.data.hardDisk}
                    """.trimIndent()

                    binding.tvData.text = dataString
                    showToast("Data loaded successfully")
                }

                is UIState.Error -> {
                    val errorMessage = state.message
                    if (errorMessage.contains("No internet connection")) {
                        showToast("No internet connection. Please connect to the internet and try again.")
                    } else {
                        showToast(errorMessage)
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSubmit.setOnClickListener {
            val name = binding.etModelName.text.toString().trim()
            val year = binding.etManuYear.text.toString().trim()
            val price = binding.etPrice.text.toString().trim()
            val cpu = binding.etModelCpu.text.toString().trim()
            val hardDisk = binding.etDiskSize.text.toString().trim()

            if (name.isEmpty() || year.isEmpty() || price.isEmpty() || cpu.isEmpty() || hardDisk.isEmpty()) {
                showToast("All fields are required")
                return@setOnClickListener
            }
            viewModel.saveDataFromViewModel(name, year, price, cpu, hardDisk)
        }
    }

    private fun clearInputFields() {
        binding.etModelName.text?.clear()
        binding.etManuYear.text?.clear()
        binding.etPrice.text?.clear()
        binding.etModelCpu.text?.clear()
        binding.etDiskSize.text?.clear()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
