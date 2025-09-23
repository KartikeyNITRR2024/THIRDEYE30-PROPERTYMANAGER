package com.thirdeye3.propertymanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROPERTY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Property {

    @Id
    @Column(name = "PROPERTY_ID", nullable = false)
    private Long id;

    @Column(name = "NO_OF_WEBSCRAPPER_MARKET", nullable = false)
    private Integer noOfWebscrapperMarket;

    @Column(name = "NO_OF_WEBSCRAPPER_USER", nullable = false)
    private Integer noOfWebscrapperUser;

    @Column(name = "START_TIME_HOUR", nullable = false)
    private Integer startTimeHour;

    @Column(name = "START_TIME_MINUTE", nullable = false)
    private Integer startTimeMinute;

    @Column(name = "START_TIME_SECOND", nullable = false)
    private Integer startTimeSecond;

    @Column(name = "END_TIME_HOUR", nullable = false)
    private Integer endTimeHour;

    @Column(name = "END_TIME_MINUTE", nullable = false)
    private Integer endTimeMinute;

    @Column(name = "END_TIME_SECOND", nullable = false)
    private Integer endTimeSecond;

    @Column(name = "MP_START_HOUR")
    private Integer mpStartHour;

    @Column(name = "MP_START_MINUTE")
    private Integer mpStartMinute;

    @Column(name = "MP_START_SECOND")
    private Integer mpStartSecond;

    @Column(name = "MP_END_HOUR")
    private Integer mpEndHour;

    @Column(name = "MP_END_MINUTE")
    private Integer mpEndMinute;

    @Column(name = "MP_END_SECOND")
    private Integer mpEndSecond;

    // 🔹 Evening Price Window
    @Column(name = "EP_START_HOUR")
    private Integer epStartHour;

    @Column(name = "EP_START_MINUTE")
    private Integer epStartMinute;

    @Column(name = "EP_START_SECOND")
    private Integer epStartSecond;

    @Column(name = "EP_END_HOUR")
    private Integer epEndHour;

    @Column(name = "EP_END_MINUTE")
    private Integer epEndMinute;

    @Column(name = "EP_END_SECOND")
    private Integer epEndSecond;
    
    @Column(name = "BUFFER_TIME_GAP_IN_SECONDS", nullable = false)
    private Integer bufferTimeGapInSeconds;
    
    @Column(name = "SIZE_TO_LOAD_STOCK", nullable = false)
    private Integer sizeToLoadStock;
    
    @Column(name = "MAXIMUM_MESSAGE_LENGTH", nullable = false)
    private Integer maximumMessageLength;
    
    @Column(name = "MAXIMUM_MESSAGE_READ_FROM_MESSAGE_BROKER", nullable = false)
    private Integer maximumMessageReadFromMessageBroker;
    
    @Column(name = "TELEGRAMBOT_USERNAME", nullable = false)
    private String telegramBotUserName;
    
    @Column(name = "TELEGRAMBOT_TOKEN", nullable = false)
    private String telegramBotToken;
    
    @Column(name = "NO_OF_TELEGRAMBOT", nullable = false)
    private Integer noOfTelegrambot;
    
    @Column(name = "MAXIMUM_NO_OF_USERS", nullable = false)
    private Integer maximumNoOfUsers;
    
    @Column(name = "MAXIMUM_NO_OF_THRESOLD_PER_GROUP", nullable = false)
    private Integer maximumNoOfThresoldPerGroup;
    
    @Column(name = "MAXIMUM_NO_OF_HOLDED_STOCK_PER_USER", nullable = false)
    private Integer maximumNoOfHoldedStockPerUser;
    
    @Column(name = "MAXIMUM_NO_OF_THRESOLD_GROUP_PER_USER", nullable = false)
    private Integer maximumNoOfThresoldGroupPerUser;
    
    @Column(name = "TIME_GAP_LIST_FOR_THRESOLD_IN_SECONDS", nullable = false)
    private String timeGapListForThresoldInSeconds;
    
}
