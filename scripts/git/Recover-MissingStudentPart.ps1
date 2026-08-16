param(
    [Parameter(Mandatory=$true)][int[]]$StudentId,
    [Parameter(Mandatory=$true)][ValidateSet('SERVICE','UT','IT','FRONTEND','ALL')][string]$Part,
    [ValidateSet('Absent','Incomplete')][string]$Reason='Incomplete',
    [string]$SolutionRemote='PresenterPrivate'
)
$ErrorActionPreference='Stop'
$root=(git rev-parse --show-toplevel).Trim(); Set-Location $root
$plan=Import-Csv 'scripts/git/track-plan.csv'
if ((git status --porcelain)) { throw 'Working tree must be clean before recovery.' }
$parts = if ($Part -eq 'ALL') { @('SERVICE','UT','IT','FRONTEND') } else { @($Part) }
foreach ($sid in $StudentId) {
    $track=('T{0:D2}' -f $sid)
    if (-not ($plan | Where-Object { $_.track -eq $track })) { throw "Unknown student/track $sid / $track" }
    foreach ($p in $parts) {
        $tag="$track-SOL-$p"
        Write-Host "Recovering $track $p ($Reason) from $SolutionRemote/$tag" -ForegroundColor Cyan
        git fetch $SolutionRemote "refs/tags/$tag:refs/tags/$tag"
        if ($LASTEXITCODE -ne 0) { throw "Cannot fetch solution tag $tag" }
        git cherry-pick $tag
        if ($LASTEXITCODE -ne 0) {
            Write-Host 'Cherry-pick stopped. Resolve the conflict manually; valid student work must not be overwritten.' -ForegroundColor Yellow
            exit 1
        }
    }
}
