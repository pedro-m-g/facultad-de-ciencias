<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Procedure extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'steps'
    ];

    protected function casts(): array
    {
        return [
            'steps' => 'array'
        ];
    }
}
