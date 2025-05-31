package com.josias.gestorcondominio.model;

import com.josias.gestorcondominio.model.Observer;

public interface Subject {
    
    void registrarObservador(Observer o);
    void removerObservador(Observer o);
    void notificarObservers();
}
