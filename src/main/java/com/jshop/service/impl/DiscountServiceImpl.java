package com.jshop.service.impl;

import com.jshop.dto.DiscountDto;
import com.jshop.exceptions.ResourceNotFoundException;
import com.jshop.model.Discount;
import com.jshop.respository.DiscountRepo;
import com.jshop.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepo discountRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public DiscountDto create(DiscountDto dto) {
        Discount discount = this.discountRepo.save(this.modelMapper.map(dto, Discount.class));
        return this.modelMapper.map(discount, DiscountDto.class);
    }

    @Override
    public DiscountDto update(int id, DiscountDto dto) {
        Discount discount = this.discountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount", "ID", id));
        discount.setDiscount(dto.getDiscount());
        discount.setCodeDiscount(dto.getCodeDiscount());
        discount.setStatus(dto.getStatus());
        discount.setUseCount(dto.getUseCount());
        this.discountRepo.save(discount);
        return this.modelMapper.map(discount, DiscountDto.class);
    }

    @Override
    public DiscountDto findById(int id) {
        Discount discount = this.discountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discount", "ID", id));
        return this.modelMapper.map(discount, DiscountDto.class);
    }

    @Override
    public DiscountDto checkCode(String code) {
        try {
            Discount discount = this.discountRepo.findByCodeDiscount(code);
            if (discount.getUseCount() > 0){
                return this.modelMapper.map(discount, DiscountDto.class);
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        DiscountDto dto = this.findById(id);
        this.discountRepo.delete(this.modelMapper.map(dto, Discount.class));
    }

    @Override
    public boolean usedCode(String code) {
        try {
            DiscountDto item = this.checkCode(code);
            int id = item.getDiscountId();
            int userCount = item.getUseCount();
            userCount -= 1;
            item.setUseCount(userCount);
            this.update(id, item);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DiscountDto> findAll() {
        List<Discount> discounts = this.discountRepo.findAll();

        List<DiscountDto> list = discounts.stream()
                .map((discount) -> this.modelMapper
                        .map(discount, DiscountDto.class)).collect(Collectors.toList());
        return list;
    }
}
