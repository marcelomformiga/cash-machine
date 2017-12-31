package com.gsw.cashmachine.domain.cash.impl;

import com.gsw.cashmachine.domain.cash.Cash;
import com.gsw.cashmachine.domain.cash.CashService;
import com.gsw.cashmachine.domain.cash.Note;
import com.gsw.cashmachine.domain.cashout.CashOutException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by eduardo on 29/12/17.
 */
@Service
public class CashOutServiceImpl implements CashService {

    @Override
    public List<Cash> processValue(final Double value) throws CashOutException {
        validateValue(value.intValue());
        List<Note> orderNotes = getDescOrderNotes();
        return calculateNotes(orderNotes, value.intValue());
    }

    private List<Cash> calculateNotes(List<Note> orderNotes, Integer value) {
        List<Cash> cashList = new ArrayList<>();
        int note = 0;
        int amount = 0;
        for (int i = 0; i < orderNotes.size(); ) {
            if (value >= orderNotes.get(i).getValue()) {
                note = orderNotes.get(i).getValue();
                amount++;
                value -= note;
            } else {
                addCash(cashList, orderNotes.get(i), amount);
                i++;
                amount = 0;
            }
        }
        return cashList;
    }

    private void addCash(List<Cash> cashList, Note note, int amount) {
        if (amount > 0) {
            cashList.add(new Cash(note, note.getValue(), amount));
        }
    }

    private List<Note> getDescOrderNotes() {
        List<Note> notes = Arrays.asList(Note.values());
        notes.sort(Collections.reverseOrder());
        return notes;
    }

    private void validateValue(Integer value) throws CashOutException {
        if (value % 10 != 0) throw new CashOutException("Invalid value");
    }

}
