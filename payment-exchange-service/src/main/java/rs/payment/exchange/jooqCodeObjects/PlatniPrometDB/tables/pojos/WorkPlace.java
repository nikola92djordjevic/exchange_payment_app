/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkPlace implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long workPlaceId;
    private Long cashRegisterId;
    private Long userId;
    private OffsetDateTime timestampOfOpen;
    private OffsetDateTime timestampOfClose;
    private OffsetDateTime timestampOfCreation;

    public WorkPlace() {}

    public WorkPlace(WorkPlace value) {
        this.workPlaceId = value.workPlaceId;
        this.cashRegisterId = value.cashRegisterId;
        this.userId = value.userId;
        this.timestampOfOpen = value.timestampOfOpen;
        this.timestampOfClose = value.timestampOfClose;
        this.timestampOfCreation = value.timestampOfCreation;
    }

    public WorkPlace(
        Long workPlaceId,
        Long cashRegisterId,
        Long userId,
        OffsetDateTime timestampOfOpen,
        OffsetDateTime timestampOfClose,
        OffsetDateTime timestampOfCreation
    ) {
        this.workPlaceId = workPlaceId;
        this.cashRegisterId = cashRegisterId;
        this.userId = userId;
        this.timestampOfOpen = timestampOfOpen;
        this.timestampOfClose = timestampOfClose;
        this.timestampOfCreation = timestampOfCreation;
    }

    /**
     * Getter for <code>public.work_place.work_place_id</code>.
     */
    public Long getWorkPlaceId() {
        return this.workPlaceId;
    }

    /**
     * Setter for <code>public.work_place.work_place_id</code>.
     */
    public void setWorkPlaceId(Long workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    /**
     * Getter for <code>public.work_place.cash_register_id</code>.
     */
    public Long getCashRegisterId() {
        return this.cashRegisterId;
    }

    /**
     * Setter for <code>public.work_place.cash_register_id</code>.
     */
    public void setCashRegisterId(Long cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    /**
     * Getter for <code>public.work_place.user_id</code>.
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.work_place.user_id</code>.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>public.work_place.timestamp_of_open</code>.
     */
    public OffsetDateTime getTimestampOfOpen() {
        return this.timestampOfOpen;
    }

    /**
     * Setter for <code>public.work_place.timestamp_of_open</code>.
     */
    public void setTimestampOfOpen(OffsetDateTime timestampOfOpen) {
        this.timestampOfOpen = timestampOfOpen;
    }

    /**
     * Getter for <code>public.work_place.timestamp_of_close</code>.
     */
    public OffsetDateTime getTimestampOfClose() {
        return this.timestampOfClose;
    }

    /**
     * Setter for <code>public.work_place.timestamp_of_close</code>.
     */
    public void setTimestampOfClose(OffsetDateTime timestampOfClose) {
        this.timestampOfClose = timestampOfClose;
    }

    /**
     * Getter for <code>public.work_place.timestamp_of_creation</code>.
     */
    public OffsetDateTime getTimestampOfCreation() {
        return this.timestampOfCreation;
    }

    /**
     * Setter for <code>public.work_place.timestamp_of_creation</code>.
     */
    public void setTimestampOfCreation(OffsetDateTime timestampOfCreation) {
        this.timestampOfCreation = timestampOfCreation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final WorkPlace other = (WorkPlace) obj;
        if (this.workPlaceId == null) {
            if (other.workPlaceId != null)
                return false;
        }
        else if (!this.workPlaceId.equals(other.workPlaceId))
            return false;
        if (this.cashRegisterId == null) {
            if (other.cashRegisterId != null)
                return false;
        }
        else if (!this.cashRegisterId.equals(other.cashRegisterId))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.timestampOfOpen == null) {
            if (other.timestampOfOpen != null)
                return false;
        }
        else if (!this.timestampOfOpen.equals(other.timestampOfOpen))
            return false;
        if (this.timestampOfClose == null) {
            if (other.timestampOfClose != null)
                return false;
        }
        else if (!this.timestampOfClose.equals(other.timestampOfClose))
            return false;
        if (this.timestampOfCreation == null) {
            if (other.timestampOfCreation != null)
                return false;
        }
        else if (!this.timestampOfCreation.equals(other.timestampOfCreation))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.workPlaceId == null) ? 0 : this.workPlaceId.hashCode());
        result = prime * result + ((this.cashRegisterId == null) ? 0 : this.cashRegisterId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.timestampOfOpen == null) ? 0 : this.timestampOfOpen.hashCode());
        result = prime * result + ((this.timestampOfClose == null) ? 0 : this.timestampOfClose.hashCode());
        result = prime * result + ((this.timestampOfCreation == null) ? 0 : this.timestampOfCreation.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WorkPlace (");

        sb.append(workPlaceId);
        sb.append(", ").append(cashRegisterId);
        sb.append(", ").append(userId);
        sb.append(", ").append(timestampOfOpen);
        sb.append(", ").append(timestampOfClose);
        sb.append(", ").append(timestampOfCreation);

        sb.append(")");
        return sb.toString();
    }
}
