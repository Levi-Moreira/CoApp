package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandler
import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandlerImpl
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenter
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenterImpl
import br.edu.ifce.lds.coapp.plans.views.PlanListView
import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 7/18/17.
 */
@Module class PlanListActivityModule(val mView: PlanListView) {

    @Provides
    @ContactScope
    fun provideView(): PlanListView = mView

    @Provides
    @ContactScope
    fun providePresenter(mPresenter: PlanListPresenterImpl): PlanListPresenter = mPresenter

    @Provides
    @ContactScope
    fun provideDataHandler(mDataHandler: PlanListDataHandlerImpl): PlanListDataHandler = mDataHandler
}