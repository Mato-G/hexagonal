package com.arantes.hexagonal.application.core.usecase;

import com.arantes.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.arantes.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import com.arantes.hexagonal.application.ports.in.DeleteCustomerInputPort;

public class DeleteCustomerByIdUserCase implements DeleteCustomerInputPort {

    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final DeleteCustomerByIdOutputPort deleteCustomerOutputPort;

    public DeleteCustomerByIdUserCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                      DeleteCustomerByIdOutputPort deleteCustomerOutputPort){
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.deleteCustomerOutputPort = deleteCustomerOutputPort;
    }

    @Override
    public void delete(String id){
        findCustomerByIdInputPort.find(id);
        deleteCustomerOutputPort.delete(id);
    }
}
