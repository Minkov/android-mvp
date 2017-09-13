package com.example.doncho.androidmvpsample.superheroes.SuperheroesPresenterTests;

import com.example.doncho.androidmvpsample.data.SuperheroesRepository;
import com.example.doncho.androidmvpsample.data.models.Superhero;
import com.example.doncho.androidmvpsample.superheroes.SuperheroesContract;
import com.example.doncho.androidmvpsample.superheroes.SuperheroesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by doncho on 9/12/17.
 */

public class loadSuperheroes {

    SuperheroesRepository repository = mock(SuperheroesRepository.class);
    SuperheroesContract.View view = mock(SuperheroesContract.View.class);

    Superhero[] superheroes = {
            new Superhero(),
            new Superhero()
    };

    @Before
    public void Setup() {
        MockitoAnnotations.initMocks(this);
        when(repository.getAll()).thenReturn(Observable.just(superheroes));
    }

    @Test
    public void loadSuperheroes_shouldCalls_setUILoadingStateTrueThenFalse() {
        SuperheroesPresenter presenter = new SuperheroesPresenter(repository);
        presenter.setView(view);

        presenter.subscribe();

        verify(view).setLoadingUIState(true);
        verify(view).setLoadingUIState(false);

    }
}
