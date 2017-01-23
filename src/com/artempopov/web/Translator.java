/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artempopov.web;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Translator class
 * Usage:
 *  Translator tr = new Translator(Locale locale); 
 *  tr.translate("Hello World") // Returns string translated to locale language
 * 
 * @author jorgen october 2016(c)
 */
public class Translator {
    ResourceBundle resources = null;
    
    private String lang;
    
    public Translator(Locale locale) {
        resources = ResourceBundle.getBundle("com.artempopov.web.Localization", locale);
        
        lang = locale.getLanguage();
    }
    
    public String translate(String string) {
        return resources.getString(string);
    }
    
    public String getLang() {
    	return lang;
    }
}