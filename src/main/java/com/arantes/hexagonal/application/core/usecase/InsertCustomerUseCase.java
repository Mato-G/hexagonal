package com.arantes.hexagonal.application.core.usecase;

import com.arantes.hexagonal.application.core.domain.Customer;
import com.arantes.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.arantes.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import com.arantes.hexagonal.application.ports.out.InsertCustomerOutputPort;
import com.arantes.hexagonal.application.ports.out.SendCpfValidationOutputPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;
    private final InsertCustomerOutputPort insertCustomerOutputPort;
    private final SendCpfValidationOutputPort sendCpfValidationOutputPort;

    public InsertCustomerUseCase(FindAddressByZipCodeOutputPort findCustomerByZipCode,
                                 InsertCustomerOutputPort insertCustomerOutputPort,
                                 SendCpfValidationOutputPort sendCpfValidationOutputPort){
        this.findAddressByZipCodeOutputPort = findCustomerByZipCode;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
        this.sendCpfValidationOutputPort = sendCpfValidationOutputPort;
    }

    @Override
    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
        sendCpfValidationOutputPort.send(customer.getCpf());
    }

}
