package com.josias.gestorcondominio.observer;

public interface Subject {
    
    void registrarObservador(Observer o);
    void removerObservador(Observer o);
    void notificarObservers();
}
