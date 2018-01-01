package com.gsw.cashmachine.unit;

import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cash.CashService;
import com.gsw.cashmachine.domain.cashmachine.CashMachineException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CashTest {

    @Autowired
    private CashService cashService;

    @Test
    public void processValue() {
        try {
            List<Cash> cash = cashService.processValue(180.0);
            Assert.assertEquals(4, cash.size());
        } catch (CashMachineException e) {
            Assert.assertNull(e.getMessage());
        }
    }

    @Test
    public void invalidValue() {
        try {
            List<Cash> cash = cashService.processValue(186.0);
        } catch (CashMachineException e) {
            Assert.assertEquals("Invalid value", e.getMessage());
        }
    }

}
