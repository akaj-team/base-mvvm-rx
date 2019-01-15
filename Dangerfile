# github comment settings
github.dismiss_out_of_range_messages

# Warn when there is a big PR
warn("Big PR, try to keep changes smaller if you can") if git.lines_of_code > 500


# detekt
# Detekt 1.0.0.RC5-6 include root project folder in file name
checkstyle_format.base_path = File.basename(Dir.getwd)
checkstyle_format.report 'app/build/reports/detekt/detekt-checkstyle.xml'

# # Android Lint
checkstyle_format.base_path = Dir.pwd

failures = Array.new
testsCount = 0
Dir.glob('app/build/test-results/testDebugUnitTest/*.xml') do |rb_file|
    junit.parse rb_file
    failures = (failures + junit.failures) unless junit.failures.empty?
    testsCount = (testsCount + junit.tests.size) unless junit.tests.empty?
end

unless failures.empty?
    fail("Tests have failed, see below for more information. (#{failures.size}/#{testsCount})")
else
    message("All tests passed. (#{testsCount}/#{testsCount})")
end

message = "Test case | Classname|\n"
message << "--- | --- |\n"
failures.each do |test|
    message << test.attributes[:name] +" | "+ test.attributes[:classname] + "|\n"
end
markdown message unless failures.empty?

# Report code coverage
require 'nokogiri'

# read and parse the old file
jacocoXml = "app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
if(File.file?(jacocoXml))
    file = File.read(jacocoXml)
    xml = Nokogiri::XML(file)

    report = ""
    xml.xpath("//report/counter[@type='INSTRUCTION']").each do |node|
        covered = node["covered"]
        missed = node["missed"]
        sum = missed.to_f + covered.to_f
        coverage_c0 = (covered.to_f * 100 / sum).round(2)
        report = "Simple Code coverage report for C0: Missed #{missed} of #{sum.to_i} (Cov. #{coverage_c0}%)"
    end

    xml.xpath("//report/counter[@type='BRANCH']").each do |node|
            covered = node["covered"]
            missed = node["missed"]
            sum = missed.to_f + covered.to_f
            coverage_c1 = (covered.to_f * 100 / sum).round(2)
            report += "\nSimple Code coverage report for C1: Missed #{missed} of #{sum.to_i} (Cov. #{coverage_c1}%)"
        end
    message(report)
else
    puts "Can not find jacoco report file: #{jacocoXml}"
end
