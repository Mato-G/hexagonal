package com.arantes.hexagonal.application.ports.out;

public interface SendCpfValidationOutputPort {

    void send(String cpf);
}
