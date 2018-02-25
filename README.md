# test-results-analyzer
Desktop app that helps to analyze results for the tests of choice.

**Test template**- template of the test containing all the questions along with answers to them with correct answer specified.

**Answered test**- concrete answered test containing sequence of choosed answers.

### Input file formats

Object representing concepts mentioned above can be loaded from files.

#### Test template

Listing 1: Example XML file encoding concrete test template

```xml
<?xml version="1.0" encoding="UTF-8"?>
<questions>
  <question>
    <title>What's your favorite color?</title>
    <answers>
      <correct>a</correct>
      <answer>
        <key>a</key>
        <value>Red</value>
      </answer>
      <answer>
        <key>b</key>
        <value>Blue</value>
      </answer>
    </answers>
  </question>

  <question>
    <title>What's your favorite meal?</title>
    <answers>
      <correct>a</correct>
      <answer>
        <key>a</key>
        <value>Banana</value>
      </answer>
      <answer>
        <key>b</key>
        <value>Pizza</value>
      </answer>
      <answer>
        <key>c</key>
        <value>Pasta</value>
      </answer>
    </answers>
  </question>
</questions>
```

#### Answered tests

Answered tests are encoded in CSV file in which each line stands for one answered test represented by ordered list of choosen answers (comma-separated).

Listing 2: CSV File containing answered tests

```
a,b
a,c
b,a
a,b
a,c
a,c
a,b
b,c
a,a 
```

