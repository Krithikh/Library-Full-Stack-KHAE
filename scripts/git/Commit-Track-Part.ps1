param(
    [Parameter(Mandatory=$true)][ValidatePattern('^T\d{2}$')][string]$TrackId,
    [Parameter(Mandatory=$true)][ValidateSet('SERVICE','UT','IT','FRONTEND','DOC-INITIAL','DOC-DEVELOPMENT','DOC-UPDATED')][string]$Part,
    [string]$Message,
    [switch]$Push
)
$ErrorActionPreference='Stop'
$root=(git rev-parse --show-toplevel).Trim(); Set-Location $root
$row=Import-Csv 'scripts/git/track-plan.csv' | Where-Object { $_.track -eq $TrackId }
if (-not $row) { throw "Unknown track $TrackId" }
if ((git status --porcelain | Where-Object { $_ -match '^UU|^AA|^DD' })) { throw 'Resolve Git conflicts before creating a synchronized track commit.' }
$className=$row.className; $operation=$row.operation
$javaBase='backend/backend.lib.mgmt/src/main/java/self/learning/backend/lib/mgmt'
$testBase='backend/backend.lib.mgmt/src/test/java/self/learning/backend/lib/mgmt'
$paths=@()
switch ($Part) {
  'SERVICE' { $paths += "$javaBase/service/impl/${operation}${className}ServiceImpl.java" }
  'UT' { $paths += "$testBase/service/impl/${operation}${className}ServiceImplTest.java"; $paths += "$javaBase/service/impl/${operation}${className}ServiceImpl.java" }
  'IT' { $paths += "$testBase/integration/${operation}${className}IntegrationTest.java"; $paths += "$javaBase/service/impl/${operation}${className}ServiceImpl.java"; $paths += "$javaBase/dao/${className}Dao.java" }
  'FRONTEND' { $paths += "frontend/frontend.lib.mgmt/src/tracks/$TrackId" }
  'DOC-INITIAL' { $paths += "student-documents/$TrackId/01_Initial_API_Contract.docx" }
  'DOC-DEVELOPMENT' { $paths += "student-documents/$TrackId/02_Progressive_Development_Guide.docx" }
  'DOC-UPDATED' { $paths += "student-documents/$TrackId/03_Updated_API_Contract.docx" }
}
$existing=@($paths | Where-Object { Test-Path $_ })
if ($existing.Count -eq 0) { throw "No expected $Part files exist for $TrackId yet." }
foreach($p in $existing){ git add -- $p }
$staged=@(git diff --cached --name-only)
if ($staged.Count -eq 0) { throw 'Nothing staged for the selected track part.' }
if (-not $Message) { $Message="$TrackId-$Part: synchronized track commit" }
Write-Host "Track: $TrackId | $($row.backend) | Service Code $($row.serviceCode)" -ForegroundColor Cyan
$staged | ForEach-Object { Write-Host "  $_" }
git commit -m $Message
if ($Push) { git push }
