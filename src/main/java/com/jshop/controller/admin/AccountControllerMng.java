package com.jshop.controller.admin;

import com.jshop.dto.AccountDto;
import com.jshop.dto.RoleDto;
import com.jshop.service.AccountService;
import com.jshop.service.RoleService;
import com.jshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/administration/accounts")
public class AccountControllerMng {
    @Autowired
    AccountService accountService;
    @Autowired
    RoleService roleService;

    @GetMapping
    public String listAccount(Model model) throws ParseException {
        DateUtils dateUtils = new DateUtils();
        List<AccountDto> list = this.accountService.findAll();
        List<RoleDto> roles = this.roleService.findAll();
        model.addAttribute("accounts", list);
        model.addAttribute("roles", roles);
        model.addAttribute("item", new AccountDto());
        model.addAttribute("createDate"
                , dateUtils.formatDate(new Date(), "dd/MM/yyyy"));
        return "/admin/list-account";
    }

    @GetMapping("/addOrEdit")
    public String addOrEdit(Model model
            , @ModelAttribute(name = "choose") String choose
            , @ModelAttribute(name = "username", binding = false) String username)
            throws ParseException {
        this.listRole(model);
        DateUtils dateUtils = new DateUtils();

        if (choose.equals("update")){
            AccountDto item = this.accountService.findByUsername(username);
            model.addAttribute("account", item);
            model.addAttribute("createDate"
                    , dateUtils.formatDate(item.getCreateDate(), "dd/MM/yyyy"));
        }else {
            model.addAttribute("account", new AccountDto());
            model.addAttribute("createDate"
                    , dateUtils.formatDate(new Date(), "dd/MM/yyyy"));
        }

        return "/admin/addOrEditAccount";
    }



    public void listRole(Model model){
        List<RoleDto> roles = this.roleService.findAll();
        model.addAttribute("roles", roles);
    }
}
