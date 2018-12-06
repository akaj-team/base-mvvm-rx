#!/usr/bin/env bash

if [ -z "${CI_PULL_REQUEST}" ]; then
    # when not pull request
    REPORTER=Saddler::Reporter::Github::CommitReviewComment
else
    REPORTER=Saddler::Reporter::Github::PullRequestReviewComment
fi

#echo "********************"
#echo "* checkstyle       *"
#echo "********************"
#cat app/build/reports/checkstyle/checkstyle.xml \
#    | bundle exec checkstyle_filter-git diff origin/master \
#    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER

echo "********************"
echo "* findbugs         *"
echo "********************"
cat app/build/reports/findbugs/findbugs.xml \
    | bundle exec findbugs_translate_checkstyle_format translate \
    | bundle exec checkstyle_filter-git diff origin/master \
    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER

echo "********************"
echo "* PMD              *"
echo "********************"
cat app/build/reports/pmd/pmd.xml \
    | bundle exec pmd_translate_checkstyle_format translate \
    | bundle exec checkstyle_filter-git diff origin/master \
    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER

echo "********************"
echo "* PMD-CPD          *"
echo "********************"
cat app/build/reports/pmd/cpd.xml \
    | bundle exec pmd_translate_checkstyle_format translate --cpd-translate \
    | bundle exec checkstyle_filter-git diff origin/master \
    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER

echo "********************"
echo "* android lint     *"
echo "********************"
cat app/build/reports/lint-results.xml \
    | bundle exec android_lint_translate_checkstyle_format translate \
    | bundle exec checkstyle_filter-git diff origin/master \
    | bundle exec saddler report --require saddler/reporter/github --reporter $REPORTER