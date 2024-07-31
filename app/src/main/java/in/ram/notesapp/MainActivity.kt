package `in`.ram.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.ram.notesapp.databinding.ActivityMainBinding
import `in`.ram.notesapp.repository.ContactRepository
import `in`.ram.notesapp.room.Contact
import `in`.ram.notesapp.room.ContactDatabase
import `in`.ram.notesapp.view.MyRecyclerViewAdapter
import `in`.ram.notesapp.viewmodel.ContactViewModel
import `in`.ram.notesapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ROOM Database
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        //View Model
        contactViewModel = ViewModelProvider(this, factory)
            .get(ContactViewModel::class.java)
        binding.contactViewModel = contactViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        contactViewModel.users.observe(this, Observer{
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, {selectItem: Contact -> listItemClicked(selectItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: Contact){
        Toast.makeText(this, "Selected name is ${selectedItem.contact_name}", Toast.LENGTH_LONG).show()
        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}