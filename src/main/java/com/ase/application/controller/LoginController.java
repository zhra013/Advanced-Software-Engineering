package com.ase.application.controller;

import com.ase.application.Service.UserService;
import com.ase.application.entity.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET)
    public String view(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
        modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
        return "entry";
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute User user,
                       BindingResult bindingResult,
                       ModelMap modelMap,
                       HttpSession session) {
        user.setUserPassword(decText(user.getUserPassword()));
        user.setUserName(decText(user.getUserName()));
        user = userService.login(user);

        if (Objects.isNull(user)) {
            ObjectError objectError = new ObjectError("username", "User name or password is invalid");
            bindingResult.addError(objectError);
        }

        if (bindingResult.hasErrors()) {
            modelMap.put("error", bindingResult);
            modelMap.put("userType", new ArrayList<>(Arrays.asList("ADMIN", "USER")));
            return "entry";
        } else {
            session.setAttribute("currentUser", user);
        }
        return "redirect:http://localhost:9090/home";
    }

    public static final String decrypt(final String encrypted, final Key key, final IvParameterSpec iv) throws InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] raw = Base64.decodeBase64(encrypted);
        byte[] stringBytes = cipher.doFinal(raw);
        String clearText = new String(stringBytes, "UTF8");
        return clearText;
    }

    public static byte[] hexStringToByteArray(String s) {

        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public String decText(String encrypTest) {
        String text = "";
        String data[] = encrypTest.split(":");
        String iv = data[0];
        byte[] encryptedByteData = hexStringToByteArray(data[1]);
        String keyString = data[2];

        IvParameterSpec ivv = new IvParameterSpec(Base64.decodeBase64(iv));
        Key k = new SecretKeySpec(Base64.decodeBase64(keyString), "AES");
        try {
            text = decrypt(Base64.encodeBase64String(encryptedByteData), k, ivv);

            System.out.println("Decrypted String:" + text);
            byte bb[] = hexStringToByteArray(text);
            text = decrypt(Base64.encodeBase64String(bb), k, ivv);
        } catch (Exception e) {
            System.out.println("----------" + e.getMessage());
        }
        return text;
    }
}
