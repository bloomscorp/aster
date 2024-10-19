package com.bloomscorp.aster.support;

import com.bloomscorp.pastebox.Pastebox;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;

public final class AsterUtility {

    private AsterUtility() {
    }

    public static @NotNull String buildUID() {
        return (
            Pastebox.randomAlphaNumericString(6, true) +
                String.format("%04d", Pastebox.getCurrentTimeInMillis() % 10000)
        );
    }

    public static @NotNull String buildRandomToken(int length) {
        return (
            Pastebox.randomAlphaNumericString(length, true) +
                String.format("%04d", Pastebox.getCurrentTimeInMillis() % 10000)
        );
    }

    public static @NotNull String buildImageFileName(@NotNull String fileName) {

        String fileExtension = "";

        int lastDotIndex = fileName.lastIndexOf(".");

        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            fileExtension = fileName.substring(lastDotIndex);
        }

        return (
            Pastebox.randomAlphaNumericString(28, true) +
                String.format("%05d", Pastebox.getCurrentTimeInMillis() % 10000) +
                fileExtension
        );
    }

    public static @NotNull String extractFileNameFromUrl(@NotNull String fileUrl) {

        String fileName = "";

        int lastForwardSlashIndex = fileUrl.lastIndexOf("/");

        if (lastForwardSlashIndex != -1 && lastForwardSlashIndex < fileUrl.length() - 1) {
            fileName = fileUrl.substring(lastForwardSlashIndex + 1);
        }

        return fileName;
    }

    public static @NotNull String generateSlug(@NotNull String name) {

        String slug = name.toLowerCase(Locale.ENGLISH).trim();

        // Remove diacritical marks (e.g., accents) from characters
        slug = Normalizer.normalize(slug, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Replace non-alphanumeric characters with hyphens
        slug = slug.replaceAll("[^a-z0-9\\-]", "-");

        // Remove duplicate hyphens
        slug = slug.replaceAll("--+", "-");

        // Remove leading/trailing hyphens
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }

    public static Date getDateWithoutTime(Long dateInMillis, String pattern) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.parse(
            sdf.format(new Date(dateInMillis))
        );
    }

    public static String getDateInStringWithoutTime(Long dateInMillis, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(new Date(dateInMillis));
    }

    public static @NotNull Long getDateInMillisecondsWithoutTime(Long dateInMillis) {
        return Instant
            .ofEpochMilli(dateInMillis)
            .atOffset(ZoneOffset.UTC)
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
            .toEpochSecond() * 1000;
    }

    public static @NotNull Long getDateInMillisecondsWithoutTime(String dateString) {
        return LocalDate
            .parse(dateString)
            .atStartOfDay(ZoneOffset.UTC)
            .toEpochSecond() * 1000;
    }

    @Contract(pure = true)
    public static @NotNull Long adjustTimeStampByDays(Long timeStampInMilliseconds, int dayCount) {

        if (dayCount == 0) return timeStampInMilliseconds;

        Long dayCountInMilliseconds = dayCount * 24L * 60L * 60L * 1000L;

        return dayCountInMilliseconds + timeStampInMilliseconds;
    }

    public static boolean isDateWithInRange(
        Long startDateInMilliseconds,
        Long endDateInMilliseconds,
        Long dateToCheckInMilliseconds
    ) {
        Date startDate = null;
        Date endDate = null;
        Date dateToCheck = null;

        try {
            startDate = AsterUtility.getDateWithoutTime(startDateInMilliseconds, "yyyy-MM-dd");
            endDate = AsterUtility.getDateWithoutTime(endDateInMilliseconds, "yyyy-MM-dd");
            dateToCheck = AsterUtility.getDateWithoutTime(dateToCheckInMilliseconds, "yyyy-MM-dd");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return (
            (dateToCheck.after(startDate) || dateToCheck.equals(startDate)) &&
                (dateToCheck.before(endDate) || dateToCheck.equals(endDate))
        );
    }
}
