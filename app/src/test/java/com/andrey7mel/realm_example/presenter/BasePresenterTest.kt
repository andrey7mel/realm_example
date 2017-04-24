package com.andrey7mel.realm_example.presenter


import com.andrey7mel.realm_example.model.Model
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(Model::class)
class BasePresenterTest {

    lateinit var basePresenter: BasePresenter
    lateinit var model: Model
    private val id = 0L

    @Before
    fun setUp() {
        model = mock(Model::class.java)
        basePresenter = BasePresenter(model)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(model)
    }

    @Test
    fun addObject() {
        basePresenter.addObject()
        verify(model).addObject()
    }

    @Test
    fun deleteObject() {
        basePresenter.deleteObject(id)
        verify(model).deleteObject(id)
    }

}