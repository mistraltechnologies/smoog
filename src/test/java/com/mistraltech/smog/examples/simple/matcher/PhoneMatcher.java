package com.mistraltech.smog.examples.simple.matcher;

import com.mistraltech.smog.core.CompositePropertyMatcher;
import com.mistraltech.smog.core.MatchAccumulator;
import com.mistraltech.smog.core.PropertyMatcher;
import com.mistraltech.smog.examples.model.Phone;
import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;

public class PhoneMatcher extends CompositePropertyMatcher<Phone> {
    private PropertyMatcher<String> codeMatcher = new PropertyMatcher<String>("code", this);
    private PropertyMatcher<String> numberMatcher = new PropertyMatcher<String>("number", this);

    private PhoneMatcher() {
        super("a Phone");
    }

    public static PhoneMatcher aPhoneThat() {
        return new PhoneMatcher();
    }

    public PhoneMatcher hasCode(String code) {
        return this.hasCode(equalTo(code));
    }

    public PhoneMatcher hasCode(Matcher<? super String> codeMatcher) {
        this.codeMatcher.setMatcher(codeMatcher);
        return this;
    }

    public PhoneMatcher hasNumber(String number) {
        return this.hasNumber(equalTo(number));
    }

    public PhoneMatcher hasNumber(Matcher<? super String> numberMatcher) {
        this.numberMatcher.setMatcher(numberMatcher);
        return this;
    }

    @Override
    protected void matchesSafely(Phone item, MatchAccumulator matchAccumulator) {
        matchAccumulator
                .matches(codeMatcher, item.getCode())
                .matches(numberMatcher, item.getNumber());
    }
}
