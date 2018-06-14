package com.malevich.server.util;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.Reservation;
import com.malevich.server.entity.TableItem;
import com.malevich.server.enums.Status;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class OrderUtil {

    public static void removeNotActualInfo(TableItem table) {
        if (!table.getOrders().isEmpty()) {
            removeInactiveOrders(table.getOrders());
        }
        if (!table.getReservations().isEmpty()) {
            removeNotActualReservations(table.getReservations());
        }
    }

    private static void removeNotActualReservations(List<Reservation> reservations) {
        Date currentDate = new Date(System.currentTimeMillis());
        reservations.removeIf(
                reservation -> !reservation.getDate().toString().equals(currentDate.toString()));
    }

    private static void removeInactiveOrders(List<Order> orders) {
        orders.sort(Comparator.comparingInt(o -> o.getStatus().getIndex()));
        if (orders.get(0).getStatus() != Status.CLOSED) {
            Order actualOrder = orders.get(0);
            orders.clear();
            orders.add(actualOrder);
        }
    }
}
