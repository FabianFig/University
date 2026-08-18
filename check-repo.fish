#!/usr/bin/env fish

set pass 0
set warn 0
set fail 0

function ok
    set -g pass (math $pass + 1)
    echo "✅ $argv"
end

function warn
    set -g warn (math $warn + 1)
    echo "⚠️  $argv"
end

function fail
    set -g fail (math $fail + 1)
    echo "❌ $argv"
end

# ─────────────────────────────────────────────
echo ""
echo "╔══════════════════════════════════════════╗"
echo "║         UNIVERSITY REPO HEALTH CHECK     ║"
echo "╚══════════════════════════════════════════╝"

# ─────────────────────────────────────────────
echo ""
echo "── Course-level READMEs ──"
for dir in */
    set dir (string trim -r -c / $dir)
    if test "$dir" = ".git"
        continue
    end
    if test -f $dir/README.md
        ok "$dir/README.md exists"
    else
        fail "$dir — missing README.md"
    end
end

# ─────────────────────────────────────────────
echo ""
echo "── Subfolder READMEs ──"
for dir in */
    set dir (string trim -r -c / $dir)
    if test "$dir" = ".git"
        continue
    end
    for subdir in $dir/*/
        if test -d $subdir
            set subdir (string trim -r -c / $subdir)
            if test -f $subdir/README.md
                ok "$subdir/README.md exists"
            else
                warn "$subdir — missing README.md"
            end
        end
    end
end

# ─────────────────────────────────────────────
echo ""
echo "── Stale READMEs (says 'No assignments added yet') ──"
for f in (find . -name README.md | sort)
    if grep -q "No assignments added yet" $f
        if grep -q "## Status" $f
            ok "$f — intentional placeholder (has ## Status)"
        else
            warn "$f — still says 'No assignments added yet'"
        end
    end
end

# ─────────────────────────────────────────────
echo ""
echo "── Naming conventions (no underscores, must use hyphens) ──"
for dir in */ */*/ 
    set dir (string trim -r -c / $dir)
    if string match -rq '^\.git' $dir
        continue
    end
    if string match -rq '_' $dir
        fail "$dir — contains underscore (use hyphens)"
    else
        ok "$dir"
    end
end

# ─────────────────────────────────────────────
echo ""
echo "── IDE/editor metadata files (should not be committed) ──"
for f in (git ls-files | grep -E '(^|/)\.project$|(^|/)\.classpath$|\.iml$|(^|/)\.idea/')
    fail "$f — IDE metadata, should be gitignored and removed"
end

# ─────────────────────────────────────────────
echo ""
echo "── Compiled artifacts that shouldn't be committed ──"
for f in (git ls-files | grep -E '\.class$|\.o$|\.out$|/a\.out$')
    fail "$f — compiled artifact, should be gitignored"
end

# ─────────────────────────────────────────────
echo ""
echo "── ANTLR generated files that shouldn't be committed ──"
for f in (git ls-files | grep -E '\.interp$|\.tokens$')
    fail "$f — ANTLR artifact, should be gitignored"
end

# ─────────────────────────────────────────────
echo ""
echo "── Python cache files ──"
for f in (git ls-files | grep -E '__pycache__|\.pyc$')
    fail "$f — Python cache, should be gitignored"
end

# ─────────────────────────────────────────────
echo ""
echo "── Empty directories ──"
for f in (git ls-files | xargs -n1 dirname | sort -u)
    if test -d $f; and test -z (ls -A $f)
        warn "$f — empty directory"
    end
end

# ─────────────────────────────────────────────
echo ""
echo "── Root .gitignore check ──"
if test -f .gitignore
    set patterns "*.class" "*.o" ".DS_Store" "__pycache__" "*.pyc" ".project" ".classpath" "*.interp" "*.tokens"
    for p in $patterns
        if grep -qF "$p" .gitignore
            ok ".gitignore covers $p"
        else
            warn ".gitignore missing pattern: $p"
        end
    end
else
    fail "No root .gitignore found"
end

# ─────────────────────────────────────────────
echo ""
echo "── README Contents ──"
for f in (find . -name README.md | sort)
    echo ""
    echo "──────────────────────────────"
    echo "📄 $f"
    echo "──────────────────────────────"
    cat $f
end

# ─────────────────────────────────────────────
echo ""
echo "╔══════════════════════════════════════════╗"
echo "║                 SUMMARY                  ║"
echo "╚══════════════════════════════════════════╝"
echo "✅ Passed : $pass"
echo "⚠️  Warnings: $warn"
echo "❌ Failed : $fail"
echo ""
