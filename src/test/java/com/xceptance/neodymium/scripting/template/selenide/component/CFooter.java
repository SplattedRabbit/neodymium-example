package com.xceptance.neodymium.scripting.template.selenide.component;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CFooter extends BasicComponent
{
    static String footerText = "Copyright (c) 2016 Xceptance Software Technologies -- This software is for demo purposes only and not meant to be used in production.";

    @Override
    protected boolean isComponentAvailable()
    {
        return $("body > footer#footer").exists();
    }

    public void validate()
    {
        // Asserts the footer contains the correct text.
        $("body > footer#footer").shouldBe(text(footerText));
    }
}
