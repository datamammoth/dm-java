package com.datamammoth.model;

public record ServerSpecs(
    Integer cpuCores,
    Integer ramMb,
    Integer diskGb,
    String diskType,
    Integer bandwidthTb
) {}
