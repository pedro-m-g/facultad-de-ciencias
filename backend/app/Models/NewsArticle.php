<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class NewsArticle extends Model
{
    use HasFactory;

    protected $fillable = [
        'title',
        'content',
        'published_at'
    ];

    protected function casts(): array
    {
        return [
            'published_at' => 'datetime',
        ];
    }

}
