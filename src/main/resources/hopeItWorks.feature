# In a Gherkin file, the first keyword must always be Feature, followed by a :, and then a description.
# You can only have a single Feature in a .feature file. Also, not all keywords
# need to have a colon after them, so keep that in mind.

# Also, grouping goes by lines in a feature file, and # acts as a line comment.
# I'm not sure if entire line needs to be a comment or if it can
# be beside another keyword. I guess we'll find out.

# Ok, I JUST installed a cucumber plugin for Java, and now, this file has
# color and line spacing suggestions! This'll be helpful because it'll
# make Gherkin syntax easier to use as eveyrthing is highlighted and the IDE looks
# for syntax mistakes. Ok, now we're getting somewhere.
Feature:
  This test will do a simple println to the console when it receives
  an HTTP request to version/. This'll be a simple cucumber test file so that
  I know how to write these tests before I start trying to come up with
  something a little more complicated.
  Scenario:
    When the client calls /version
    Then they will receive the response "1.0"
    Then FINITO