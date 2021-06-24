package com.example.task1.service;

import com.example.task1.entity.Address;
import com.example.task1.payload.AddressDto;
import com.example.task1.payload.ApiResponse;
import com.example.task1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;


    public List<Address> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses;
    }

    public Address getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }


    public ApiResponse addAddress(AddressDto addressDto) {
        boolean exists = addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber());
        if (exists) {
            return new ApiResponse("Bunday manzil mavjud", false);
        }
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);

        return new ApiResponse("Manzil saqlandi", true);
    }

    public ApiResponse editAddress(Integer id, AddressDto addressDto) {
        boolean exists = addressRepository.existsByStreetAndHomeNumberAndIdNot( addressDto.getStreet(),addressDto.getHomeNumber(),id);
        if (exists) {
            return new ApiResponse("Bunday manzil mavjud", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return new ApiResponse("Address not found", false);
        }
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);

        return new ApiResponse("successfully edited", true);
    }


    public ApiResponse deleteAddress(Integer id) {
        try {

            addressRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error. Address not found", false);
        }
    }
}
