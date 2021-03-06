package com.itacademy.jd2.po.hotel.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.po.hotel.dao.api.model.IMessage;
import com.itacademy.jd2.po.hotel.service.IMessageService;
import com.itacademy.jd2.po.hotel.service.IReCaptchaService;
import com.itacademy.jd2.po.hotel.web.converter.MessageFromDTOConverter;
import com.itacademy.jd2.po.hotel.web.dto.MessageDTO;

@Controller
@RequestMapping(value = "/contacts")
public class ContactsController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private MessageFromDTOConverter fromDTOConverter;
    @Autowired
    private IReCaptchaService reCaptchaService;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
    // @valid стоит для валидации
    public ModelAndView send(final HttpServletRequest req,
            @Valid @ModelAttribute("formModel") final MessageDTO formModel, final BindingResult result) {
        final Map<String, Object> hashMap = new HashMap<>();
        if (req.getMethod().equalsIgnoreCase("get")) {
            return new ModelAndView("contacts");
        }
        if (result.hasErrors()) {
            return new ModelAndView("contacts", hashMap);
        } else {
            // Verify CAPTCHA.
            String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
            boolean valid = reCaptchaService.verify(gRecaptchaResponse);

            if (!valid) { // req.setAttribute("errorString", "Captcha
                          // invalid!");
                hashMap.put("error", reCaptchaService.ERROR_STRING);
                return new ModelAndView("contacts", hashMap);
            }

            final IMessage entity = fromDTOConverter.apply(formModel);
            try {
                messageService.save(entity);
                hashMap.put("error", "Your message was sended");
            } catch (MessagingException e) {
                hashMap.put("error", "Your message was not sended. Try again later.");
                hashMap.put("formMidel", formModel);
            }
            return new ModelAndView("contacts", hashMap);
        }
    }
}
