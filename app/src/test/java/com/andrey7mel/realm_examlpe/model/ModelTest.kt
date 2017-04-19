package com.andrey7mel.realm_examlpe.model

import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import rx.Observable


@RunWith(PowerMockRunner::class)
@PrepareForTest(Realm::class, RealmQuery::class)
class ModelTest {

    lateinit var realm: Realm
    lateinit var model: Model
    lateinit var realmQuery: RealmQuery<DataObject>

    private val id = 0L

    @Before
    fun setUp() {
        mockStatic(Realm::class.java)
        realm = mock(Realm::class.java)

        `when`(Realm.getDefaultInstance()).thenReturn(realm)

        @Suppress("UNCHECKED_CAST")
        realmQuery = mock(RealmQuery::class.java) as RealmQuery<DataObject>

        `when`(realm.where(DataObject::class.java)).thenReturn(realmQuery)

        model = Model()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(realm)
    }

    @Test
    fun getObjects() {
        @Suppress("UNCHECKED_CAST")
        val expectedRealmResults = mock(RealmResults::class.java) as RealmResults<DataObject>

        `when`(realmQuery.findAllSorted("id")).thenReturn(expectedRealmResults)
        `when`(realm.where(DataObject::class.java)).thenReturn(realmQuery)

        val actual = model.getObjects(realm)

        verify(realm).where(DataObject::class.java)
        assertTrue(actual == expectedRealmResults)
    }

    @Test
    fun getObjectsObservable() {
        @Suppress("UNCHECKED_CAST")
        val realmQuery = mock(RealmQuery::class.java) as RealmQuery<DataObject>

        @Suppress("UNCHECKED_CAST")
        val expectedRealmResults = mock(RealmResults::class.java) as RealmResults<DataObject>


        val resultObservable = Observable.just(expectedRealmResults)

        `when`(expectedRealmResults.asObservable()).thenReturn(resultObservable)
        `when`(realmQuery.findAllSorted("id")).thenReturn(expectedRealmResults)
        `when`(realm.where(DataObject::class.java)).thenReturn(realmQuery)

        val actualObservable = model.getObjectsObservable()

        verify(realm).where(DataObject::class.java)
        verify(realm, never()).close()

        var actual: List<DataObject>? = null
        val sub = actualObservable.subscribe({ actual = it })

        assertEquals(expectedRealmResults, actual)

        sub.unsubscribe()
        verify(realm).close()
    }


    @Test
    fun getObject() {
        @Suppress("UNCHECKED_CAST")
        val realmQueryLocal = mock(RealmQuery::class.java) as RealmQuery<DataObject>

        val expectedObject = DataObject()

        `when`(realmQuery.equalTo("id", id)).thenReturn(realmQueryLocal)
        `when`(realmQueryLocal.findFirst()).thenReturn(expectedObject)

        assertEquals(expectedObject, model.getObject(realm, id))
        verify(realm).where(DataObject::class.java)
    }


    @Test
    fun getObjectAsync() {
        @Suppress("UNCHECKED_CAST")
        val realmQueryLocal = mock(RealmQuery::class.java) as RealmQuery<DataObject>

        val expectedObject = DataObject()

        `when`(realmQuery.equalTo("id", id)).thenReturn(realmQueryLocal)
        `when`(realmQueryLocal.findFirstAsync()).thenReturn(expectedObject)

        assertEquals(expectedObject, model.getObjectAsync(realm, id))
        verify(realm).where(DataObject::class.java)
    }
}