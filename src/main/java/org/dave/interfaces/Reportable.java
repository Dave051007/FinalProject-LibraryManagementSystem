package org.dave.interfaces;

import org.dave.domain.Item;

import java.util.List;
import java.util.Map;

public interface Reportable {
    Map<Item.Status, List<String>> report();
}
