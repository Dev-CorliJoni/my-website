try {
    $Assets_path = $NAS_SHARE_PROJECTS + '\\my-website\\assets'
    $Assets_dest = $WORKSPACE + '\\src\\'

    Write-Host $Assets_path
    Write-Host $Assets_dest
    
    exit 0
}
catch {
    exit 1
}
