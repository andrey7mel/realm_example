package com.andrey7mel.realm_example.presenter

import com.andrey7mel.realm_example.model.DataObject
import com.andrey7mel.realm_example.model.Model
import com.andrey7mel.realm_example.view.fragments.rx.RxView
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import rx.Observable

@RunWith(PowerMockRunner::class)
@PrepareForTest(Model::class)
class RxPresenterTest {


    lateinit var presenter: RxPresenter
    lateinit var model: Model
    lateinit var view: RxView

    @Before
    fun setUp() {
        model = mock(Model::class.java)
        presenter = RxPresenter(model)
        view = mock(RxView::class.java)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(model)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onViewCreatedEmptyData() {
        assertNull(presenter.subscription)

        val emptyList = listOf<DataObject>()
        val observable = Observable.just(emptyList)

        `when`(model.getObjectsObservable()).thenReturn(observable)
        presenter.onViewCreated(view)
        verify(model).getObjectsObservable()
        verify(view).showEmpty()
    }


    @Test
    fun onViewCreatedWithData() {
        assertNull(presenter.subscription)

        val dataObject = DataObject()
        val list = listOf<DataObject>(dataObject)
        val observable = Observable.just(list)

        `when`(model.getObjectsObservable()).thenReturn(observable)

        presenter.onViewCreated(view)
        verify(model).getObjectsObservable()
        verify(view).showData(list)
    }


    @Test
    fun onDestroyView() {
        assertNull(presenter.subscription)

        val subscription = Observable.never<DataObject>().subscribe()
        presenter.subscription = subscription

        assertFalse(subscription.isUnsubscribed)

        presenter.onDestroyView()

        assertTrue(subscription.isUnsubscribed)
    }

}