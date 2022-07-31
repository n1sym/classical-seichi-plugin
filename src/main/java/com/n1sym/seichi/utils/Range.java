package com.n1sym.seichi.utils;

public class Range {
  public static int[] calc(String direction, int seichi_level) {
    int range[] = new int[6];
    if (seichi_level < 10) {
      range = range_1(direction);
    } else if (10 <= seichi_level && seichi_level < 20) {
      range = range_2(direction);
    } else if (20 <= seichi_level && seichi_level < 30) {
      range = range_3(direction);
    } else if (30 <= seichi_level && seichi_level < 30) {
      range = range_4(direction);
    } else if (40 <= seichi_level) {
      range = range_5(direction);
    }
    return range;
  }

  private static int[] range_1(String direction) {
    int range[] = new int[6];
    if (direction == "W") {
      range = new int[] { -1, 1, -1, 1, 0, 0 };
    } else if (direction == "E") {
      range = new int[] { -1, 1, -1, 1, 0, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 0, -1, 1, -1, 1 };
    } else if (direction == "N") {
      range = new int[] { 0, 0, -1, 1, -1, 1 };
    } else if (direction == "DOWN") {
      range = new int[] { -1, 1, 0, 0, -1, 1 };
    }
    return range;
  }

  private static int[] range_2(String direction) {
    int range[] = new int[6];
    if (direction == "W") {
      range = new int[] { -1, 1, -1, 1, 0, 2 };
    } else if (direction == "E") {
      range = new int[] { -1, 1, -1, 1, -2, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 2, -1, 1, -1, 1 };
    } else if (direction == "N") {
      range = new int[] { -2, 0, -1, 1, -1, 1 };
    } else if (direction == "DOWN") {
      range = new int[] { -1, 1, -2, 0, -1, 1 };
    }
    return range;
  }

  private static int[] range_3(String direction) {
    int range[] = new int[6];
    if (direction == "W") {
      range = new int[] { -2, 2, -1, 1, 0, 2 };
    } else if (direction == "E") {
      range = new int[] { -2, 2, -1, 1, -2, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 2, -1, 1, -2, 2 };
    } else if (direction == "N") {
      range = new int[] { -2, 0, -1, 1, -2, 2 };
    } else if (direction == "DOWN") {
      range = new int[] { -2, 2, -2, 0, -1, 1 };
    }
    return range;
  }

  private static int[] range_4(String direction) {
    int range[] = new int[6];
    if (direction == "W") {
      range = new int[] { -2, 2, -1, 3, 0, 2 };
    } else if (direction == "E") {
      range = new int[] { -2, 2, -1, 3, -2, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 2, -1, 3, -2, 2 };
    } else if (direction == "N") {
      range = new int[] { -2, 0, -1, 3, -2, 2 };
    } else if (direction == "DOWN") {
      range = new int[] { -2, 2, -4, 0, -1, 1 };
    }
    return range;
  }

  private static int[] range_5(String direction) {
    int range[] = new int[6];
    if (direction == "W") {
      range = new int[] { -2, 2, -1, 3, 0, 4 };
    } else if (direction == "E") {
      range = new int[] { -2, 2, -1, 3, -4, 0 };
    } else if (direction == "S") {
      range = new int[] { 0, 4, -1, 3, -2, 2 };
    } else if (direction == "N") {
      range = new int[] { -4, 0, -1, 3, -2, 2 };
    } else if (direction == "DOWN") {
      range = new int[] { -2, 2, -4, 0, -2, 2 };
    }
    return range;
  }
}
