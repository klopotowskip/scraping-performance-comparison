package org.example;

import java.util.List;

record Quote(String text, String author, List<String> tags) {
}