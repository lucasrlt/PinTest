package com.pindex.main.repositories

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pindex.main.models.ExperienceDto
import com.pindex.main.utils.Constants
import kotlinx.coroutines.tasks.await

/**
 * Experience repository that fetches the data from Firestore.
 */
class FirebaseExperienceRepository : ExperienceRepository {

    // Firestore instance
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Last experience used to fetch the next chunk of data
    private var lastDocumentSnapshot: DocumentSnapshot? = null

    override suspend fun getPage(limit: Long): List<ExperienceDto> {
        var query = firestore.collection(Constants.FIRESTORE_EXPERIENCES_COLLECTION)
                .whereEqualTo("status", "listed")

        // Fetch the next chunk of data from the last experience
        lastDocumentSnapshot?.let {
            query = query.startAfter(it)
        }

        val result = query
                .limit(limit)
                .get()
                .await()

        // Update the last experience
        lastDocumentSnapshot = result.documents[result.size() - 1]

        return result.toObjects(ExperienceDto::class.java)
    }
}