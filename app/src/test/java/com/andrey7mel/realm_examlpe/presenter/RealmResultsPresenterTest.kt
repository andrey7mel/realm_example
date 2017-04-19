package com.andrey7mel.realm_examlpe.presenter

import com.andrey7mel.realm_examlpe.model.DataObject
import com.andrey7mel.realm_examlpe.model.Model
import io.realm.Realm
import io.realm.RealmResults
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Realm::class, Model::class)
class RealmResultsPresenterTest {

    lateinit var presenter: RealmResultsPresenter
    lateinit var model: Model
    lateinit var realm: Realm

    private val id = 0L

    @Before
    fun setUp() {
        model = mock(Model::class.java)
        presenter = RealmResultsPresenter(model)

        mockStatic(Realm::class.java)
        realm = mock(Realm::class.java)

        `when`(Realm.getDefaultInstance()).thenReturn(realm)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(model)
    }


    @Test
    fun onCreate() {
        assertNull(presenter.realm)
        presenter.onCreate()
        assertEquals(realm, presenter.realm)
    }


    @Test
    fun getObjects() {
        presenter.realm = realm

        @Suppress("UNCHECKED_CAST")
        val expected = mock(RealmResults::class.java) as RealmResults<DataObject>

        `when`(model.getObjects(realm)).thenReturn(expected)

        val actual = presenter.getObjects()
        Assert.assertTrue(actual == expected)

        verify(model).getObjects(realm)
    }

    @Test
    fun onDestroy() {
        presenter.realm = realm
        verify(realm, never()).close()
        presenter.onDestroy()
        verify(realm).close()
    }

}