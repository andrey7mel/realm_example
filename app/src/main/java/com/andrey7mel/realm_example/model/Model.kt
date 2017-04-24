package com.andrey7mel.realm_example.model

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import rx.Observable
import javax.inject.Inject


class Model @Inject
internal constructor() {


    fun getObjects(realm: Realm): RealmResults<DataObject> {
        return realm.where(DataObject::class.java).findAllSorted("id")
    }

    fun getObjectsObservable(): Observable<List<DataObject>> {
        val realm = Realm.getDefaultInstance()
        return realm.where(DataObject::class.java).findAllSorted("id").asObservable().doOnUnsubscribe { realm.close() }.map { it }
    }

    fun deleteObject(id: Long) {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync {
                findFirstDataObject(id, it).deleteFromRealm()
            }
        }
    }

    fun addObject() {
        Realm.getDefaultInstance().use { it.executeTransactionAsync { it.insertOrUpdate(DataObject()) } }
    }

    fun getObject(realm: Realm, id: Long): DataObject? {
        return findFirstDataObject(id, realm)
    }

    fun addTestObject() {
        Realm.getDefaultInstance().use { it.executeTransactionAsync { it.insertOrUpdate(DataObject(0)) } }
    }


    fun getObjectObservable(realm: Realm, id: Long): Observable<DataObject?> {
        return realm.where(DataObject::class.java).equalTo("id", id).findFirstAsync()
                .asObservable<DataObject?>().filter({ it?.isLoaded }).filter { it?.isValid }
    }

    //Crash!
    fun testCrashAsync() {
        Realm.getDefaultInstance().use {
            val firstObject = it.where(DataObject::class.java).findFirstAsync()
            log(firstObject.id)
        }
    }

    fun getObjectAsync(realm: Realm, id: Long): DataObject? {
        return realm.where(DataObject::class.java).equalTo("id", id).findFirstAsync()
    }

    fun syncTransaction() {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val dataObject = DataObject(0)
                it.insertOrUpdate(dataObject)
            }
        }
    }


    fun asyncTransaction() {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync({
                it.insertOrUpdate(DataObject(0))
            }, {
                log("OnSuccess")
            }, {
                log("onError")
                it.printStackTrace()
            })
        }
    }

    fun asyncTransactionException() {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync({
                log("RuntimeException now")
                throw RuntimeException("Async Exception")
            }, {
                log("OnSuccess")
            }, {
                log("onError")
                it.printStackTrace()
            })
        }
    }

    fun log(log: Any) {
        Log.d("Realm Example", log.toString())
    }

    fun clearRealm() {
        Realm.getDefaultInstance().use({ it.executeTransaction { it.deleteAll() } })
    }

    private fun findFirstDataObject(id: Long, realm: Realm): DataObject
            = realm.where(DataObject::class.java).equalTo("id", id).findFirst()


}
