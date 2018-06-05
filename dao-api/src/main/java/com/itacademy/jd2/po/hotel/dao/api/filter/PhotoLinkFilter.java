package com.itacademy.jd2.po.hotel.dao.api.filter;

public class PhotoLinkFilter extends AbstractFilter {

    private Integer roomId;

    private Integer roomNumber;

    private Integer userAccountId;

    private boolean fetchRoom;

    private boolean fetchUserAccount;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(final Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(final Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(final Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public boolean getFetchRoom() {
        return fetchRoom;
    }

    public void setFetchRoom(final boolean fetchRoom) {
        this.fetchRoom = fetchRoom;
    }

    public boolean getFetchUserAccount() {
        return fetchUserAccount;
    }

    public void setFetchUserAccount(final boolean fetchUserAccount) {
        this.fetchUserAccount = fetchUserAccount;
    }
}
