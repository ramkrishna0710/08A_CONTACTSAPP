package `in`.ram.notesapp.repository

import `in`.ram.notesapp.room.Contact
import `in`.ram.notesapp.room.ContactDAO

//  Repository: acts as a bridge between the ViewModel an Data Source
class ContactRepository(private val contactDAO: ContactDAO) {

    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact):Long {
        return contactDAO.insetContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}