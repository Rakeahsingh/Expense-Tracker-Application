package com.rkcoding.expensetrackerapplication.app_features.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class FirebaseTransactionRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): FirebaseTransactionRepository {

    private val _transaction = MutableStateFlow<List<Transaction>>(emptyList())
    override val transaction: StateFlow<List<Transaction>>
        get() = _transaction.asStateFlow()

    private val transactionListenerRegistration: ListenerRegistration? = null

    override suspend fun addTransaction(transaction: Transaction) {
        if (transaction.transactionId.isNotEmpty()){
            Log.d("TransactionId", "Transaction id is : ${transaction.transactionId} ")
            val userId = firebaseAuth.currentUser?.uid ?: return
            fireStore.collection("users")
                .document(userId)
                .collection("transactions")
                .document(transaction.transactionId)
                .set(transaction, SetOptions.merge())
                .await()
        }else{
            Log.d("TransactionId", "Transaction id is null: ${transaction.transactionId} ")
        }
    }

    override suspend fun getTransactionById(id: String): Transaction? {
        val userId = firebaseAuth.currentUser?.uid ?: return null

        try {
            val querySnapshot = fireStore.collection("users")
                .document(userId)
                .collection("transaction")
                .whereEqualTo("transactionId", id)
                .get()
                .await()

            val documents = querySnapshot.documents

            if (documents.isNotEmpty()){
                val document = documents[0]
                val transactionId = document.getString("transactionId") ?: return null
                val transactionTitle = document.getString("transactionTitle") ?: ""
                val date = document.getString("date") ?: ""
                val entryDate = document.getString("entryDate") ?: ""
                val accountType = document.getString("accountType") ?: ""
                val transactionAmount = document.getDouble("transactionAmount") ?: 0.0
                val category = document.getString("category") ?: ""
                val transactionType = document.getString("transactionType") ?: ""

                return Transaction(transactionId, transactionTitle, date, entryDate, accountType, transactionAmount, category, transactionType)

            }else{
                return null
            }

        }catch (e: Exception){
            e.printStackTrace()
            Log.d("TransactionId", "getTransactionById error message: ${e.message}")
            return null
        }
    }

    override suspend fun deleteTransaction(transactionId: String) {
        val userId = firebaseAuth.currentUser?.uid ?: return

        val query = fireStore.collection("users")
            .document(userId)
            .collection("transaction")
            .whereEqualTo("transactionId", transactionId)

        query.get().addOnSuccessListener {
            for (document in it){
                document.reference.delete()
            }
        }.await()
    }

    override suspend fun getTransaction(): List<Transaction> {
        val userId = firebaseAuth.currentUser?.uid ?: return emptyList()

        return fireStore.collection("users")
            .document(userId)
            .collection("transaction")
            .get()
            .await()
            .documents
            .mapNotNull { document ->
               Transaction(
                   transactionId = document.getString("transactionId") ?: return@mapNotNull null,
                   transactionTitle = document.getString("transactionTitle") ?: "",
                   date = document.getString("date") ?: "",
                   entryDate = document.getString("entryDate") ?: "",
                   accountType = document.getString("accountType") ?: "",
                   transactionAmount = document.getDouble("transactionAmount") ?: 0.0,
                   category = document.getString("category") ?: "",
                   transactionType = document.getString("transactionType") ?: ""
               )
            }.reversed()
    }

    override suspend fun realTimeTransactionData() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        stopTransactionRealTimeUpdate()

        val query = fireStore.collection("users")
            .document(userId)
            .collection("transaction")

        query.addSnapshotListener { querySnapshot, firebaseFireStoreException ->
            if (firebaseFireStoreException != null){
                return@addSnapshotListener
            }

            val transactions = mutableListOf<Transaction>()
            querySnapshot?.documents?.forEach { document ->

                Log.d("document", "Document data: ${document.data}")

                val transactionId = document.getString("transactionId") ?: return@forEach
                val transactionTitle = document.getString("transactionTitle") ?: ""
                val date = document.getString("date") ?: ""
                val entryDate = document.getString("entryDate") ?: ""
                val accountType = document.getString("accountType") ?: ""
                val transactionAmount = document.getDouble("transactionAmount") ?: 0.0
                val category = document.getString("category") ?: ""
                val transactionType = document.getString("transactionType") ?: ""

                val transaction = Transaction(transactionId, transactionTitle, date, entryDate, accountType, transactionAmount, category, transactionType)
                transactions.add(transaction)
            }
            _transaction.value = transactions
        }
    }

    override fun stopTransactionRealTimeUpdate() {
        transactionListenerRegistration?.remove()
    }
}