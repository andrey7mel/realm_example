package com.andrey7mel.realm_examlpe.DI

import com.andrey7mel.realm_examlpe.model.Model
import com.andrey7mel.realm_examlpe.presenter.RealmResultsPresenter
import dagger.Provides
import javax.inject.Singleton

@dagger.Module
class Module {

    @Provides
    @Singleton
    internal fun providePresenter(model: Model): RealmResultsPresenter {
        return RealmResultsPresenter(model)
    }

    @Provides
    @Singleton
    internal fun provideModel(): Model {
        return Model()
    }
}
