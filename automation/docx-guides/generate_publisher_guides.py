from pathlib import Path

# Publisher follows the same frozen five-operation teaching structure as Category.
# Reuse the accepted generator structure so the depth, order and styling remain synchronized.
source_path = Path('automation/docx-guides/generate_category_guides.py')
source = source_path.read_text(encoding='utf-8')

replacements = [
    ("Documents/Student-Guides/Category", "Documents/Student-Guides/Publisher"),
    ("T20", "T25"), ("T19", "T24"), ("T18", "T23"), ("T17", "T22"), ("T16", "T21"),
    ("Student 20", "Student 25"), ("Student 19", "Student 24"), ("Student 18", "Student 23"), ("Student 17", "Student 22"), ("Student 16", "Student 21"),
    ("Category", "Publisher"), ("category", "publisher"), ("categories", "publishers"),
    ("PROGRAMMING", "PUB-PRENTICE"), ("Programming", "Prentice Hall"), ("programming", "prentice"),
    ("DATABASE", "PUB-ADDISON"), ("Database Systems", "Addison-Wesley"),
    ("LEGACY", "PUB-OLD"), ("Archived Publisher", "Archived Publisher"),
    ("NETWORK", "PUB-SAGE"), ("Computer Networks", "Sage Publications"),
    ("SECURITY", "PUB-NEW"),
    ("ID 4", "ID 5"), ("4 / PUB-SAGE", "5 / PUB-SAGE"), ("Publisher 4", "Publisher 5"),
]

for old, new in replacements:
    source = source.replace(old, new)

# The Publisher training model uses IDs 1,2,3,4 plus an additional unused active row ID 5.
# Ensure the generated prose names the intended dependency and unused rows.
source = source.replace(
    "Sage Publications - ACTIVE and unused by current Books",
    "Sage Publications - ACTIVE and unused by current Books")
source = source.replace(
    "PUB-PRENTICE - ACTIVE and used by active Books",
    "PUB-PRENTICE - ACTIVE and used by active Books")

namespace = {'__name__': '__main__'}
exec(compile(source, str(source_path), 'exec'), namespace)
