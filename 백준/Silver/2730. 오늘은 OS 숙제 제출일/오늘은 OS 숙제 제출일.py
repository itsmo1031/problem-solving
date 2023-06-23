import calendar
import datetime as dt


def get_year():
    global dm, dd, dy, rm, rd
    if rm == 2 and rd == 29:
        if calendar.isleap(dy):
            return dt.date(dy, rm, rd)
        return dt.date(2020, rm, rd)
    if abs(rm - dm) >= 6:
        if rm > dm:
            return dt.date(dy - 1, rm, rd)
        else:
            return dt.date(dy + 1, rm, rd)
    return dt.date(dy, rm, rd)


def fmt_date(date):
    return '{d.month}/{d.day}/{d.year}'.format(d=date)


for __ in range(int(input())):
    deadline, report = input().split()
    dm, dd, dy = map(int, deadline.split('/'))
    rm, rd = map(int, report.split('/'))
    deadline = dt.date(dy, dm, dd)
    report = get_year()
    res = deadline - report

    answer = "SAME DAY"
    if abs(res.days) > 7:
        answer = "OUT OF RANGE"
    elif res.days < 0:
        answer = f'{fmt_date(report)} IS {abs(res.days)} {"DAY" if abs(res.days) == 1 else "DAYS"} AFTER'
    elif 0 < res.days:
        answer = f'{fmt_date(report)} IS {abs(res.days)} {"DAY" if abs(res.days) == 1 else "DAYS"} PRIOR'

    print(answer)
